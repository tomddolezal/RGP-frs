import React, { Component } from 'react';
import { Form } from 'informed';
import AppState from '../AppState';

class SignUpPage extends Component {

 constructor(props){
    super(props);
    this.state = {
      text: "Sign Up"
    };
  } 

  signUp = (event) => {
    console.log("signed up!");
    AppState.user = {};
    AppState.app.forceUpdate();
  }


  render() {
    return (
      <div className="container">
        <h2>Sign Up</h2>
        <hr/>
        <Form onSubmit={this.signUp}>
         <div className="form-group">
            <label for="name">Name:</label>
            <input type="text" className="form-control" id="name" placeholder="Enter name" name="name"/>
          </div>
          <div className="form-group">
            <label for="email">Email:</label>
            <input type="email" className="form-control" id="email" placeholder="Enter email" name="email"/>
          </div>
          <div className="form-group">
            <label for="pwd">Password:</label>
            <input type="password" className="form-control" id="pwd" placeholder="Enter password" name="pswd"/>
          </div>
          <div className="form-group">
            <button className="btn btn-custom" style={{float:"right"}}>Sign Up</button>
          </div>
        </Form>
      </div>
    );
  }
}

export default SignUpPage;
