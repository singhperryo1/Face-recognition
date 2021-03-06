import React, {Component} from 'react';
import Navigation from './components/Navigation/Navigation' ; 
import Logo from './components/Logo/Logo' ; 
import Particles from 'react-particles-js';
import ImageLinkForm from './components/ImageLinkForm/ImageLinkForm.js' ; 
import Rank from './components/Rank/Rank.js' ;
import SignIn from './components/SignIn/SignIn.js' ; 
import Register from './components/Register/Register.js' ; 
import Clarifai from 'clarifai';
import FaceRecognition from './components/FaceRecognition/FaceRecognition.js' ;  
import './App.css';

const app = new Clarifai.App({
 apiKey:  ('40c875c38b6f43bdb1e5c8024f46a91b')
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

const initialState = {
   input: '', 
      imageUrl: '', 
      box:{}, 
      route: 'signIn', 
      isSignedIn :false,
      user : 
      {
        id : '', 
        name : '' , 
        email : '', 
        entries : 0, 
        joined : ''
      }
}

class App extends Component {
  constructor ()
  {
    super(); 
    this.state = initialState ; 
  }

  loadUser = (data) =>
  {
    this.setState ({user:{
      id : data.id , 
      name : data.name, 
      email : data.email, 
      entries : data.entries, 
      joined : data.joined
    }})
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
     app.models
     .predict (
      Clarifai.FACE_DETECT_MODEL,
      this.state.input)
     .then(response => {
      if (response) {
        fetch('https://evening-tundra-45665.herokuapp.com/image',
        {
          method : 'put', 
          headers : {'Content-Type' : 'application/json'}, 
          body : JSON.stringify({
            id : this.state.user.id 
          })
        })
        .then(response => response.json())
        .then(count => {
          this.setState (Object.assign(this.state.user, {entries: count}))
        })
      }
    
      this.displayFaceBox(this.calculateFaceLoc (response))
    })
     .catch (err => console.log(err))      
  }

  onInputChange = (event) =>
  {
    this.setState({input :event.target.value}); 
  }

  onRouteChange = (route) =>
  {
    if (route === 'signout')
    {
      this.setState(initialState) ; 
    }
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
     <Rank 
     name ={this.state.user.name} 
     entries = {this.state.user.entries}
      />
     <ImageLinkForm 
     onInputChange = {this.onInputChange} 
     onSubmit = {this.onSubmit} />
     <FaceRecognition box = {box} imageUrl = {imageUrl} />
     </div>
     : (
     route === 'signIn' 
     ? <SignIn loadUser = {this.loadUser} onRouteChange = {this.onRouteChange} />
     : <Register loadUser = {this.loadUser} onRouteChange = {this.onRouteChange} />
     )
    }
     </div>
  );
  }
}

export default App;
