import React, { Component } from 'react';

class Good extends Component {

  handleAdd = (e) => {
    e.preventDefault();
    alert(JSON.stringify(this.props.eachGood.name));
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
        <button className="addCart" onClick={this.props.handleAdd}>add to order</button>
      </div>
    );
  }
}

export default Good;