import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class HospitalsPage extends Component {

  render() {
    return (
      // ID is {this.props.match.params.id}
      <div className="container">
        <div className="card">
          <div className="card-body">
            <div className="flex-container">
              <div className="row">
                <div className="col-sm-6">
                  <h3><a href="#">St. Micheals Hospital</a></h3>
                </div>
                <div className="col-sm-6">
                  <p className="float-right">Downtown Toronto</p>
                </div>
              </div>             
            </div>
            <hr/> 
            <p>30 Bond Street Toronto</p>
            <p><a href="#">Website</a></p>
            <Link to={`/referral/${this.props.match.params.id}`}>
              <p><a href="#">Write a Referral</a></p>
            </Link>
            <hr/> 
            <h4>Contact</h4>
            <table class="table">
              <thead>
                <tr>
                  <th>Service</th>
                  <th>Phone</th>
                  <th>Fax</th>
                  <th>Wait Time</th>
                </tr>
              </thead>
              <tbody>                
                  <tr>
                    <td>Name</td>
                    <td>416-555-5555</td> 
                    <td>416-444-4444</td>
                    <td>4 Months</td>
                  </tr>                
              </tbody>
            </table>
            
          </div>
        </div>
      </div>
    );
  }
}

export default HospitalsPage;
