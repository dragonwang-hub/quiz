import React, { Component } from 'react';

class SingleOrder extends Component {

  handleDelete = (e) => {
    e.preventDefault();
    alert(JSON.stringify(this.props.eachOrder.name));
  }

  render() {
    return (
      <div className="orderInfo">
        <td className="orderName">
          {this.props.eachOrder.name}
        </td>
        <td className="orderPrice">
          {this.props.eachOrder.price}
        </td>
        <td className="orderCount">
          {this.props.eachOrder.count}
        </td>
        <td className="orderUnit">
          {this.props.eachOrder.unit}
        </td>
        <td className="orderUnit">
          <button className="deleteOrder" onClick={(e) => this.handleDelete(e)}>删除</button>
        </td>
      </div>
    );
  }


}

export default SingleOrder;