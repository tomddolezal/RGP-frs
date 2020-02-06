import React, { Component } from 'react';
import { Text } from 'informed';

class ServiceRequested extends Component {

  hospital() {
    return (
      <div className="form-group">
          <h6>Hospital Requested</h6>
          <Text className="form-control" field="hospital" id="field-hospital" required />
        </div>
      );
  }

  service() {
    return (
      <div className="form-group">
          <h6>Service Requested</h6>
          <Text className="form-control" field="service" id="field-service" required />
        </div>
      );

  }

  render() {
    return (
      <div>
        
        {/* Hospital */}
        {this.hospital()}

        {/* Service */}
        {this.service()}

      </div>
    );
  }


}

export default ServiceRequested;
