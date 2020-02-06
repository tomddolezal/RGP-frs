import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import AppState from '../AppState';

class NavBar extends Component {

  getMainBar() {
    return AppState.isLoggedIn() ? this.getUserBar() : this.getAnonBar();
  }

  getAnonBar() {
    return (
      <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
        <form className="form-inline ml-auto  my-2 my-lg-0">
          <Link to={`/login`}>
            <button className="btn btn-info my-2 my-sm-0" type="submit">Login</button>
          </Link>
          <Link to={`/signup`}>
            <button className="btn btn-outline-light my-2 my-sm-0 ml-2" type="submit">Sign Up</button>
          </Link>
        </form>
      </div>
    );
  }

  getUserBar() {
    return (
      <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
          <li className="nav-item active">
            <Link className="nav-link" to={`/search`}>Search</Link>
          </li>
          <li className="nav-item active">
            <Link className="nav-link" to={`/services`}>Services</Link>
          </li>
          <li className="nav-item active">
            <Link className="nav-link" to={`/hospitals`}>Hospitals</Link>
          </li>
        </ul>
        <form className="form-inline my-2 my-lg-0" onSubmit={this.logout}>
          <button className="btn btn-info my-2 my-sm-0" type="submit">Logout</button>
        </form>
      </div>
    );
  }

  logout() {
    AppState.user = null;
    AppState.app.forceUpdate();
  }

  render() {
    return (
      <div>
        <Link className="navbar-brand" to={`/`}>
        <img src="https://www.rgptoronto.ca/wp-content/uploads/2018/04/RGP-COLOUR2.png" alt="logo.png" width="300" height="42" align="middle" hspace="20" vspace="20"/>
        </Link>
        <nav className="navbar navbar-expand-sm bg-custom navbar-dark">
        <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        {this.getMainBar()}
        </nav>
      </div>
    );
  }
}

export default NavBar;
