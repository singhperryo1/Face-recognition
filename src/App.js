import React, {Component} from 'react';
import Navigation from './components/Navigation/Navigation' ; 
import Logo from './components/Logo/Logo' ; 
import Particles from 'react-particles-js';
import ImageLinkForm from './components/ImageLinkForm/ImageLinkForm.js' ; 
import Rank from './components/Rank/Rank.js' ;
import SignIn from './components/SignIn/SignIn.js' ; 
import Register from './components/Register/Register.js' ; 
import FaceRecognition from './components/FaceRecognition/FaceRecognition.js' ;  
import './App.css';
import Clarifai from 'clarifai' ; 

const app = new Clarifai.App({
 apiKey: '40c875c38b6f43bdb1e5c8024f46a91b'
});
const particlesOptions = 
{
                particles: {
                  number : 
                  {
                    value : 30, 
                    density : {
                      enable : true,
                      value_area: 800, 
                    }
                  }
                }
}

class App extends Component {
  constructor ()
  {
    super(); 
    this.state = {
      input: '', 
      imageUrl: '', 
      box : {}, 
      route: 'signIn', 
      isSignedIn :false,
    }
  }

  calculateFaceLoc = (data) =>
  {

   const clarifaiFace = data.outputs[0].data.regions[0].region_info.bounding_box ; 
   const image = document.getElementById('inputimage');
   const width = Number(image.width);
   const height = Number(image.height);   
   return {
    leftcol: clarifaiFace.left_col * width,  
    topRow: clarifaiFace.top_row * height, 
    rightCol: width - (clarifaiFace.right_col * width), 
    bottomRow: height - (clarifaiFace.bottom_row * height),  
   }
  }


  displayFaceBox = (box) =>
  { 
    this.setState({box: box}); 
  }


  onSubmit = () =>
  {
    this.setState({imageUrl: this.state.input}) ; 
    app.models.predict(
      Clarifai.FACE_DETECT_MODEL,
      this.state.input)
     .then(response =>
      this.displayFaceBox(this.calculateFaceLoc (response))).catch (err => console.log(err)) 
  }

  onInputChange = (event) =>
  {
    this.setState({input :event.target.value}); 
  }

  onRouteChange = (route) =>
  {
    if (route === 'signout')
      this.setState({isSignedIn: false}) ; 
    else if (route === 'home')
      this.setState({isSignedIn: true}); 

   this.setState({route: route}) ;  
  }

  render () {
    const {isSignedIn, imageUrl, route, box } = this.state; 
    return (
      <div className="App">
      <Particles className ='particles'
      params = {particlesOptions}
      />
     <Navigation isSignedIn={isSignedIn} onRouteChange = {this.onRouteChange} />
     { route === 'home' 
     ?
     <div>
     <Logo />
     <Rank />
     <ImageLinkForm 
     onInputChange = {this.onInputChange} 
     onSubmit = {this.onSubmit} />
     <FaceRecognition box={box} imageUrl = {imageUrl} />
     </div>
     : 
     (
     route === 'signIn' 
     ? <SignIn onRouteChange = {this.onRouteChange} />
     : <Register onRouteChange = {this.onRouteChange} />
     )
    }
     </div>
  );
  }
}

export default App;
