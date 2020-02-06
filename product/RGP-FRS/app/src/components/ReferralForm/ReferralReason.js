import React, { Component } from 'react';
import { Text, Checkbox, Select, Option, withFieldState } from 'informed';

class ReferralReason extends Component {

  medical() {
    const MultiSelect = ({ fieldState }) => {
      return fieldState.value === true ?
        <React.Fragment>
          <Select
            field="reason-medical-detail"
            id="select-reason-medical-detail"
            multiple
            className="form-control"
            required >
            <Option value="Mobility">Mobility</Option>
            <Option value="Falls">Falls</Option>
            <Option value="Incontinence">Incontinence</Option>
            <Option value="Pain management">Pain management</Option>
            <Option value="Medication / polypharmacy">Medication / polypharmacy</Option>
            <Option value="Sleep">Sleep</Option>
            <Option value="Weight loss / nutrition">Weight loss / nutrition</Option>
          </Select>
        </React.Fragment>
        : null
    };

    const OptionalMultiSelect = withFieldState('reason-medical')(MultiSelect);

    return (
      <div className="form-group">
        <div className="form-check">
          <Checkbox className="form-check-input" field="reason-medical" id="checkbox-reason-medical" />
          <label className="form-check-label" htmlFor="checkbox-reason-medical">Medical / Physical</label>
        </div>
        <OptionalMultiSelect />
      </div>
    );

  }

  cognitive() {
    const MultiSelect = ({ fieldState }) => {
      return fieldState.value === true ?
        <React.Fragment>
          <Select
            field="reason-cognitive-detail"
            id="select-reason-cognitive-detail"
            multiple
            className="form-control"
            required >
            <Option value="Delirium">Delirium</Option>
            <Option value="Verbal / physical aggression">Verbal / physical aggression</Option>
            <Option value="Cognition / dementia">Cognition / dementia</Option>
            <Option value="Delusions / hallucinations">Delusions / hallucinations</Option>
            <Option value="Depression">Depression</Option>
            <Option value="Wandering">Wandering</Option>
          </Select>
        </React.Fragment>
        : null
    };

    const OptionalMultiSelect = withFieldState('reason-cognitive')(MultiSelect);

    return (
      <div className="form-group">
        <div className="form-check">
          <Checkbox className="form-check-input" field="reason-cognitive" id="checkbox-reason-cognitive" />
          <label className="form-check-label" htmlFor="checkbox-reason-cognitive">Cognitive / Behavioural</label>
        </div>
        <OptionalMultiSelect />
      </div>
    );
  }

  psychosocial() {
    const MultiSelect = ({ fieldState }) => {
      return fieldState.value === true ?
        <React.Fragment>
          <Select
            field="reason-psychosocial-detail"
            id="select-reason-psychosocial-detail"
            multiple
            className="form-control"
            required >
            <Option value="Caregiver / family issues">Caregiver / family issues</Option>
            <Option value="Elder abuse">Elder abuse</Option>
            <Option value="Social isolation">Social isolation</Option>
          </Select>
        </React.Fragment>
        : null
    };

    const OptionalMultiSelect = withFieldState('reason-psychosocial')(MultiSelect);

    return (
      <div className="form-group">
        <div className="form-check">
          <Checkbox className="form-check-input" field="reason-psychosocial" id="checkbox-reason-psychosocial" />
          <label className="form-check-label" htmlFor="checkbox-reason-psychosocial">Psychosocial</label>
        </div>
        <OptionalMultiSelect />
      </div>
    );
  }

  functional() {
    const MultiSelect = ({ fieldState }) => {
      return fieldState.value === true ?
        <React.Fragment>
          <Select
            field="reason-functional-detail"
            id="select-reason-functional-detail"
            multiple
            className="form-control"
            required >
            <Option value="ADL/IADL decline">ADL/IADL decline</Option>
            <Option value="Home safety">Home safety</Option>
          </Select>
        </React.Fragment>
        : null
    };

    const OptionalMultiSelect = withFieldState('reason-functional')(MultiSelect);

    return (
      <div className="form-group">
        <div className="form-check">
          <Checkbox className="form-check-input" field="reason-functional" id="checkbox-reason-functional" />
          <label className="form-check-label" htmlFor="checkbox-reason-functional">Functional</label>
        </div>
        <OptionalMultiSelect />
      </div>
    );
  }

  other() {
    const OtherReasonDetail = ({ fieldState }) => {
      return fieldState.value === true ?
        <React.Fragment>
          <Text className="form-control" field="reason-other-detail" placeholder="Please specify" required />
        </React.Fragment>
        : null
    };

    const OptionalOtherReasonField = withFieldState('reason-other')(OtherReasonDetail);

    return (
      <div className="form-group">
          <div className="form-check">
            <Checkbox className="form-check-input" field="reason-other" id="checkbox-reason-other" />
            <label className="form-check-label" htmlFor="checkbox-reason-other">Other</label>
          <OptionalOtherReasonField />
        </div>

      </div>
    );
  }

  render() {
    return (
      <div>
        <h3>Reasons For Referral</h3>

        {/* Medical */}
        {this.medical()}

        {/* Cognitive */}
        {this.cognitive()}

        {/* Psychosocial */}
        {this.psychosocial()}

        {/* Functional */}
        {this.functional()}

        {/* Other */}
        {this.other()}

      </div>
    );
  }


}

export default ReferralReason;
