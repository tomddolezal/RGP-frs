import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class SearchListItem extends Component {
 
  render() {

    return (
      <div className="pt-4">
        <div className="card">
          <div className="card-body">
            <div className="flex-container">
              <div className="row">
                <div className="col-sm-6">
                <Link to={`/hospitals/${this.props.hospital.ID}`}>
                  <h3>{this.props.hospital.hospital}</h3>
                  </Link>
                </div>
                <div className="col-sm-6">                  
                  <h4 className="float-right">{this.props.hospital.distance}</h4>
                </div>
              </div>             
            </div>
            <table className="table">
              <thead>
                <tr>
                  <th>Service</th>
                  <th>Wait Time</th>
                </tr>
              </thead>
              <tbody>
                {this.props.hospital.services.map((service, i) => (
                  <tr key={i}>
                    <td>{service.service}</td>
                    <td>{service.waittime}</td>                  
                  </tr>)
                )}
              </tbody>
            </table>
            
          </div> 
          <div className="card-footer">
            <div className="row">
                <div className="col-sm-6">
                <Link to={`/hospitals/${this.props.hospital.ID}`}>
                  <h6>More Information</h6>
                  </Link>
                </div>
                <div className="col-sm-6">
                  <Link to={`/referral/${this.props.hospital.ID}`}>
                   <h6 className="float-right"><a href="#">Write a Referral</a></h6>
                  </Link>
                </div>
              </div>
          </div>
        </div>
      </div>
    );
  }
}

export default SearchListItem;
