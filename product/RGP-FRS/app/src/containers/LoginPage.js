import React, { Component } from 'react';
import { Form, Text, Checkbox } from 'informed';
import AppState from '../AppState';

class LoginPage extends Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  doctorLogin(value) {
    console.log("Doctor: ", JSON.stringify(value));
    AppState.user = {};
    AppState.app.forceUpdate();
  }

  providerLogin(value) {
    console.log("Provider: ", JSON.stringify(value));
    AppState.user = {};
    AppState.app.forceUpdate();
  }

  render() {
    return (
      <div className="container">
        <div className="tab-content" id="nav-tabContent">
          <div className="tab-pane fade show active" id="nav-provider" role="tabpanel" aria-labelledby="provider-login-tab">
            <Form id="provider-login-form" onSubmit={this.providerLogin}>
              <div className="form-group">
                <label htmlFor="provider-email">Email:</label>
                <Text type="email" className="form-control" id="provider-email" placeholder="Enter email" field="email" />
              </div>
              <div className="form-group">
                <label htmlFor="provider-pwd">Password:</label>
                <Text type="password" className="form-control" id="provider-pwd" placeholder="Enter password" field="pwd" />
              </div>
              <div className="form-group form-check">
                <label className="form-check-label">
                  <Checkbox className="form-check-input" type="checkbox" field="remember" /> Remember me
            </label>
              </div>
              <div className="form-group">
                <button className="btn btn-custom" style={{float:"right"}} type="submit">Submit</button>
              </div>
            </Form>
          </div>
        </div>
      </div>
    );
  }
}

export default LoginPage;
