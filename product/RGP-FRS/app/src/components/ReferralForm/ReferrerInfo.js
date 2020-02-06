import React, { Component } from 'react';
import { Text, Scope } from 'informed';

class ReferrerInfo extends Component {

  familyMD() {
    return (
      <Scope scope="family-md" id="scope-family-md">
        <div className="form-group">
          <div>
            <label htmlFor="row-family-md">Family MD:</label>
          </div>
          <div className="form-group row" id="row-family-md">
            <div className="col">
              <Text className="form-control" placeholder="Name" field="name" required />
            </div>
            <div className="col">
              <Text className="form-control" placeholder="Telephone" field="telephone" required />
            </div>
            <div className="col">
              <Text className="form-control" placeholder="Fax" field="fax" required />
            </div>
          </div>
        </div>
      </Scope>
    );

  }

  referringSource() {
    return (
      <Scope scope="referring-source" id="scope-referring-source">
        <div className="form-group">
          <div>
            <label htmlFor="row-referring-source">Referring Source:</label>
          </div>
          <div className="form-group row" id="row-referring-source">
            <div className="col">
              <Text className="form-control" placeholder="Name" field="name" required />
            </div>
            <div className="col">
              <Text className="form-control" placeholder="Telephone" field="telephone" required />
            </div>
            <div className="col"></div>
          </div>
        </div>
      </Scope>
    );
  }

  referringPhysician() {
    return (
      <Scope scope="referring-physician" id="scope-referring-physician">
        <div className="form-group">
          <div>
            <label htmlFor="row-referring-physician">Referring Physician:</label>
          </div>
          <div className="form-group row" id="row-referring-physician">
            <div className="col">
              <Text className="form-control" placeholder="Name" field="name" required />
            </div>
            <div className="col">
              <Text className="form-control" placeholder="Telephone" field="telephone" required />
            </div>
            <div className="col">
              <Text className="form-control" placeholder="Fax" field="fax" required />
            </div>
          </div>
        </div>
      </Scope>
    );
  }

  billing() {
    return (
    <div className="form-group">
        <div>
          <label htmlFor="field-billing-no">Billing No.:</label>
        </div>
        <Text className="form-control" field="billing-no" id="field-billing-no"required />
      </div>
    );
  }

  render() {
    return (
      <div>
        <h3>Referrer Information</h3>

        {/* Family MD */}
        {this.familyMD()}

        {/* Referring Source */}
        {this.referringSource()}

        {/* Referring Physician */}
        {this.referringPhysician()}

        {/* Billing */}
        {this.billing()}

      </div>
    );
  }


}

export default ReferrerInfo;
