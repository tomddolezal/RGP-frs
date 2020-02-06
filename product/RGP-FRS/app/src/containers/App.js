import React, { Component } from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import SignUpPage from './SignUpPage';
import LoginPage from './LoginPage';
import ProviderView from './ProviderView';
import ProviderInfo from './ProviderInfo';
import SearchPage from './SearchPage';
import ReferralPage from './ReferralPage'
import NavBar from '../components/NavBar';
import HospitalsPage from './HospitalsPage';
import AppState from '../AppState';


class App extends Component {

  constructor(props) {
    super(props);
    AppState.app = this;
  }

  componentDidMount() {
    fetch("/hospitals")
      .then(res => res.text())
      .then(
        (result) => {
          this.setState({
            isLoaded: true,
            apiString: result
          });
        },
        (error) => {
          this.setState({
            isLoaded: true,
            error
          });
        }
      )
  }

  render() {
    return (
    <div>
      <NavBar/>
      <div className="container-fluid pt-4">
        <Switch>
            <Route exact path="/"  component={SearchPage} />
            <Route path="/signup" component={SignUpPage} />
            <Route path="/login" component={LoginPage} />
            <Route path="/providerview/:serviceID" component={ProviderView} />
            <Route path="/providerinfo/:hospitalID" component={ProviderInfo} />
            <Route path="/search" component={SearchPage} />
            <Route path="/hospitals/:id" component={HospitalsPage} />
            <Route path="/referral/:id" component={ReferralPage} />
            <Redirect to="/" />
          </Switch>
      </div>
    </div>
    );
  }
}

export default App;
