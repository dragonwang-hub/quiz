import React, { Component } from 'react';


class Good extends Component {

  handleAdd = async (e) => {
    e.preventDefault();
    alert(JSON.stringify(this.props.eachGood));
  }

  render() {
    return (
      <div className="goodInfo">
        <p className="goodImg">
          <img src={this.props.eachGood.name} alt="good" />
        </p>
        <p className="goodName">
          {this.props.eachGood.name}
        </p>
        <p className="goodUnitPrice">
          {this.props.eachGood.price}元/{this.props.eachGood.unit}
        </p>
        <button className="addCart" onClick={(e) => this.handleAdd(e)}>add to order</button>
      </div>
    );
  }
}

export default Good;