import React, { Component } from 'react';
import SearchListItem from '../components/SearchListItem';
import HositalsInfo from '../resources/hospitalinfo.json';

const SearchMode = {
  DEFAULT: 0,
  LOADING: 1
};

class SearchPage extends Component {

 constructor(props){
    super(props);
    this.state = {
      loading: false,
      searchAddress: "",
      hospitals: HositalsInfo.hospitals
    };
    //this.getAddressFromGeoLocation();
    //TODO: Make Back-end request for Hopsital Information
    //GET request for Hospital Information
  } 

  //Update the search address with input 
  handleChange = (event) => {
    this.setState({
      ...this.state,
      searchAddress: event.target.value
    });
  }

  //If geolocation is enabled, get the nearest address
  getAddressFromGeoLocation = () => {
    //Ask user to enable GeoLocation
    if (navigator.geolocation) {
      //If got geolocation, get address from current position
      navigator.geolocation.getCurrentPosition(function(position) {
        var pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
        //Ask Google Maps API for address at coordinates
        document.geocoder.geocode({'location': pos}, function(results, status) {
          if (status === 'OK') {
            if (results[0]) {
              //Update search input field with address
              if (this.state.searchAddress === ""){
                this.setState({
                  ...this.state,
                  searchAddress: results[0].formatted_address
                });
              }
            } else {
              console.log("no geolocation address results found");
            }
          } else {
            console.log("Geocoder failed due to: "+ status);            
          }
        }.bind(this))
      }.bind(this), function(error) { 
        console.log("Geoconder failed due to:"+error);
      });
    }
  }

  getSearchButtonHTML(mode)
  {
    if(mode === SearchMode.DEFAULT)
    {
      return (<span>Search</span>);
    }
    else
    {
      return (
        <span><i className='fa fa-spinner fa-spin'></i> Searching</span>
      );
    }
  }

  onSearch = (event) => {
    //TODO: Add filter to search
    //Stop Page from refreshing
    event.preventDefault()
    this.setState({
      ...this.state,
      loading: true,
    });

    //Prepare request 
    //Get Addresses of all hospitals
    let startIndex = 0;
    let endIndex = 23;
    let destinationArray = this.getAddressArray(startIndex, endIndex);
    let hopsitalsCopy = [...this.state.hospitals];
    //Uses Google API Distance Matrix
    //https://developers.google.com/maps/documentation/javascript/distancematrix
    //TODO: Fix bug where only 25 Hospitals can be queried at a time
    document.distanceMatrixService.getDistanceMatrix({
        origins: [this.state.searchAddress],
        destinations: destinationArray,
        travelMode: "BICYCLING"
    }, this.onDistanceResponse.bind(this, startIndex, endIndex, hopsitalsCopy));

  }

  onDistanceResponse = (startIndex, endIndex, hopsitalsCopy, response, status) => {
    /* Example Response
    response: {
        destinationAddresses: [],
        originAddresses: [],
        rows: [{
          elements: [{
            "status": "OK"
            "distance": {text: "10.8 km", value: 10839},
            "duration": {text: "41 mins", value: 2434}
          }]
        }]
    }
    */
    //Check for errors
    if (!response || !response.rows || !response.rows[0]){
      console.log("Error: Hospital distance response not received")
      console.log(response);
      return;
    }
    if (startIndex === undefined || !endIndex || !hopsitalsCopy){
      console.log("Error: index or hospitals copy undefined");
      return;
    }
    //Recieve list of distances to locations
    let hospitalDistances = response.rows[0].elements;
    //let hospitalsWithDistances = [...this.state.hospitals];
    for(let i=0; i < hospitalDistances.length; i++){
      hopsitalsCopy[startIndex + i].distance = hospitalDistances[i].status === "OK" ? 
                          hospitalDistances[i].distance.text : "";
      hopsitalsCopy[startIndex + i].distanceValue = hospitalDistances[i].status === "OK" ? 
                          hospitalDistances[i].distance.value : "";                                 
    }

    //Re-run request on any remaining hospitals
    //let endIndex = endIndex;
    if (endIndex < hopsitalsCopy.length - 1) {      
      let newEndIndex = endIndex + 23 < hopsitalsCopy.length ? endIndex + 23 : hopsitalsCopy.length - 1;
      let newStartIndex = endIndex;
      let destinationArray = this.getAddressArray(newStartIndex, newEndIndex);
      document.distanceMatrixService.getDistanceMatrix({
          origins: [this.state.searchAddress],
          destinations: destinationArray,
          travelMode: "BICYCLING"
      }, this.onDistanceResponse.bind(this, newStartIndex, newEndIndex, hopsitalsCopy));
      return;
    }

    //Sort list of hospitals by distance
    hopsitalsCopy.sort(this.hospitalDistanceComparator);

    //Update list appearance
    this.setState({
      ...this.state,
      loading: false,
      hospitals: hopsitalsCopy
    });      
  }

  getAddressArray = (startIndex, endIndex) => {
    let addressArray = [];
    for (let i = startIndex; i <= endIndex; i++){
      addressArray.push(this.state.hospitals[i].address);
    }
    // this.state.hospitals.forEach((hospital) => {
    //   if (hospital.address){
    //     addressArray.push(hospital.address);
    //   }      
    // });
    return addressArray;
  }

  // addDistanceToHospitals = (destinationAddresses, hospitalDistances) => {
  //   let hospitalsWithDistances = [];
  //   for(let i=0; i < hospitalDistances.length; i++){
  //     let hospital = this.hospitalFromAddress(destinationAddresses[i]);
  //     hospital.distance = hospitalDistances[i].status === "OK" ? 
  //                           hospitalDistances[i].distance.text : "";
  //     hospitalsWithDistances.push(hospital);
  //   }
  //   this.state.setState({
  //     ...this.state,
  //     loading: false,
  //     hospitals: hospitalsWithDistances
  //   });
  // }

  hospitalDistanceComparator = (hospitalA, hospitalB) => {
    if (!hospitalA.distance || !hospitalB.distance){
      //A hospital doesn't have a distance
      return 0;
    }
    return hospitalA.distanceValue - hospitalB.distanceValue;
  }
 
  render() {
    //TODO: Add links to SearchListItems
    const searchText = this.getSearchButtonHTML(this.state.loading ? SearchMode.LOADING : SearchMode.DEFAULT);
    return (
      <div className="container">
        <form onSubmit={this.onSearch}>
          <div className="form-group row">
            <div className="col-8">
              <input className="form-control form-control-lg" id="searchInput" type="text"
                placeholder="Search by Address" value={this.state.searchAddress} onChange={this.handleChange}/>
            </div>
            <div className="col-2">
              <select className="form-control form-control-lg">
                <option selected value="any">Any</option>
                <option value="gem">GEM</option>
                <option value="ger">Geriatric</option>
              </select>
            </div>
            <div className="col-2">
              <button type="submit" disabled={this.state.loading} className="btn btn-custom mb-2 form-control-lg">{searchText}</button>
            </div>
          </div>
        </form>
        <hr/>        
        {this.state.hospitals.map((hospital) =>
          <SearchListItem key={hospital.ID} hospital={hospital} />
        )}
      </div>
    );
  }
}

export default SearchPage;