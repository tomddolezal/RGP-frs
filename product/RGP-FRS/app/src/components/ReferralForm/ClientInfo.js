import React, { Component } from 'react';
import { Text, Checkbox, Scope, RadioGroup, Radio, Select, Option, withFieldState } from 'informed';

class ClientInfo extends Component {

  translator() {
    const TranslatorLanguage = ({ fieldState }) => {
      return fieldState.value === true ?
        <React.Fragment>
          <Text className="form-control" field="translator-lang" placeholder="Language" required />
        </React.Fragment>
        : null
    };

    const OptionalTranslatorLanguageField = withFieldState('translator')(TranslatorLanguage);

    return (
      <div className="form-group">
        <div className="form-row">
          <div className="col-md-2">
            <label htmlFor="">Translator needed:</label>
          </div>
          <div className="col-md-1">
            <Checkbox field="translator" id="checkbox-translator" />
          </div>
          <div className="col-md-6">
            <OptionalTranslatorLanguageField />
          </div>
        </div>
      </div>
    );

  }

  ccac() {
    const CaseManager = ({ fieldState }) => {
      return fieldState.value === "Yes" ?
        <React.Fragment>
          <div className="form-group">
            <label htmlFor="form-group-client-name">CCAC Case Manager:</label>
            <div id="form-group-client-name" className="form-group row">
              <div className="col">
                <Text className="form-control" id="client-ccac-name" placeholder="Name" field="ccac-name" required />
              </div>
              <div className="col">
                <Text className="form-control" id="client-ccac-tel" placeholder="Telephone" field="ccac-tel" required />
              </div>
            </div>
          </div>
        </React.Fragment>
        : null
    };

    const OptionalCCACCaseManager = withFieldState('ccac')(CaseManager);

    return (
      <div className="form-group">
        <div>
          <label htmlFor="radio-group-ccac">Is CCAC involved?</label>
        </div>
        <RadioGroup field="ccac" id="radio-group-">
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Yes" id="radio-ccac-yes" />
            <label className="form-check-label" htmlFor="radio-ccac-yes">Yes</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="No" id="radio-ccac-no" />
            <label className="form-check-label" htmlFor="radio-ccac-no">No</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Unsure" id="radio-ccac-unsure" />
            <label className="form-check-label" htmlFor="radio-ccac-unsure">Unsure</label>
          </div>
        </RadioGroup>

        <OptionalCCACCaseManager />

      </div>
    );
  }

  name() {
    return (
      <div className="form-group">
        <label htmlFor="form-group-client-name">Name of Client:</label>
        <div id="form-group-client-name" className="form-group row">
          <div className="col">
            <Text className="form-control" id="client-surname" placeholder="Surname" field="surname" required />
          </div>
          <div className="col">
            <Text className="form-control" id="client-first-name" placeholder="First name" field="first-name" required />
          </div>
        </div>
      </div>
    );
  }

  sex() {
    return (
      <div className="form-group">
        <div>
          <label htmlFor="radio-group-sex">Sex:</label>
        </div>
        <RadioGroup field="sex" id="radio-group-sex">
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Male" id="radio-male" />
            <label className="form-check-label" htmlFor="radio-male">M</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Female" id="radio-female" />
            <label className="form-check-label" htmlFor="radio-female">F</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Unspecified" id="radio-unspecified" />
            <label className="form-check-label" htmlFor="radio-unspecified">X</label>
          </div>
        </RadioGroup>
      </div>
    );
  }

  address() {
    return (
      <Scope scope="address" id="scope-address">
        <div className="form-group">
          <div>
            <label htmlFor="scope-address">Address:</label>
          </div>
          <div className="form-group row">
            <div className="col">
              <Text className="form-control" placeholder="Street Name and Number" field="street-address" required />
            </div>
            <div className="col">
              <Text className="form-control" placeholder="Apt. No." field="apt-no" />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group col-md-6">
              <Text className="form-control" placeholder="City" field="city" required />
            </div>
            <div className="form-group col-md-4">
              <Text className="form-control" placeholder="Province" field="province" required />
            </div>
            <div className="form-group col-md-2">
              <Text className="form-control" placeholder="Postal Code" field="postal-code" required />
            </div>
          </div>
        </div>
      </Scope>
    );
  }

  telephone() {
    return (
      <div className="form-group">
        <label htmlFor="client-telephone">Telephone:</label>
        <Text className="form-control" id="client-telephone" field="telephone" required />
      </div>
    );
  }

  livesAlone() {
    return (
      <div className="form-group">
        <div>
          <label htmlFor="radio-group-alone">Lives alone:</label>
        </div>
        <RadioGroup field="livesAlone" id="radio-group-alone">
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Yes" id="radio-alone-yes" />
            <label className="form-check-label" htmlFor="radio-alone-yes">Yes</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="No" id="radio-alone-no" />
            <label className="form-check-label" htmlFor="radio-alone-no">No</label>
          </div>
        </RadioGroup>
      </div>
    );
  }

  marital() {
    return (
      <div className="form-group">
        <label htmlFor="select-status">Marital status:</label>
        <Select className="form-control" field="marital-status" id="select-status" required>
          <Option value="" disabled>Please select...</Option>
          <Option value="single">Single</Option>
          <Option value="married">Married</Option>
          <Option value="common-law">Common Law</Option>
          <Option value="separated">Separated</Option>
          <Option value="divorced">Divorced</Option>
          <Option value="widowed">Widowed</Option>
        </Select>
      </div>
    );
  }

  healthCard() {
    return (
      <div className="form-group">
        <div>
          <label htmlFor="row-health-card">Health Card:</label>
        </div>
        <div className="form-row" id="row-health-card">
          <div className="col-md-8">
            <Text className="form-control" placeholder="Health Card Number" field="health-card-number" required />
          </div>
          <div className="col-md-2">
            <Text className="form-control" placeholder="Version" field="health-card-version" required />
          </div>
        </div>
      </div>
    );
  }

  dob() {
    return (
      <div className="form-group">
        <label htmlFor="client-dob">DOB:</label>
        <Text className="form-control" id="client-dob" field="dob" required />
      </div>
    );
  }

  altContact() {
    return (
      <div className="form-group">
        <div>
          <label htmlFor="row-alt-contact">Alternative Contact:</label>
        </div>
        <div className="form-row" id="row-alt-contact">
          <div className="col-md-4">
            <Text className="form-control" placeholder="Name" field="alt-contact-name" required />
          </div>
          <div className="col-md-4">
            <Text className="form-control" placeholder="Relationship" field="alt-contact-relationship" required />
          </div>
          <div className="col-md-4">
            <Text className="form-control" placeholder="Telephone" field="alt-contact-telephone" required />
          </div>
        </div>
      </div>
    );
  }

  contact() {
    return (
      <div className="form-group">
        <label htmlFor="client-contact-person">Contact Person:</label>
        <Text className="form-control" id="client-contact-person" field="contact-person" required />
      </div>
    );
  }

  aware() {
    return (
      <div className="form-group">
        <div>
          <label htmlFor="radio-group-aware">Is client/substitute decision maker aware of referral?</label>
        </div>
        <RadioGroup field="aware" id="radio-group-aware">
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Yes" id="radio-aware-yes" />
            <label className="form-check-label" htmlFor="radio-aware-yes">Yes</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="No" id="radio-aware-no" />
            <label className="form-check-label" htmlFor="radio-aware-no">No</label>
          </div>
        </RadioGroup>
      </div>
    );
  }

  homeBound() {
    return (
      <div className="form-group">
        <div>
          <label htmlFor="radio-group-homebound">Is patient homebound?</label>
        </div>
        <RadioGroup field="homebound" id="radio-group-">
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="Yes" id="radio-homebound-yes" />
            <label className="form-check-label" htmlFor="radio-homebound-yes">Yes</label>
          </div>
          <div className="form-check form-check-inline">
            <Radio className="form-check-input" value="No" id="radio-homebound-no" />
            <label className="form-check-label" htmlFor="radio-homebound-no">No</label>
          </div>
        </RadioGroup>
      </div>
    );
  }



  render() {
    return (
      <div>
        <h3>Client Information</h3>

        {/* Name */}
        {this.name()}

        {/* Sex */}
        {this.sex()}

        {/* Address */}
        {this.address()}

        {/* Telephone */}
        {this.telephone()}

        {/* Lives alone */}
        {this.livesAlone()}

        {/* Marital status */}
        {this.marital()}

        {/* Health Card */}
        {this.healthCard()}

        {/* TODO: DOB */}
        {this.dob()}

        {/* Alternative Contact */}
        {this.altContact()}

        {/* Contact Person */}
        {this.contact()}

        {/* Translator */}
        {this.translator()}

        {/* Aware */}
        {this.aware()}

        {/* Home bound */}
        {this.homeBound()}

        {/* CCAC */}
        {this.ccac()}

      </div>
    );
  }


}

export default ClientInfo;
