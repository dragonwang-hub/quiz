import React, { Component } from 'react';

class SingleOrder extends Component {

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
          <button className="deleteOrder" onClick={this.props.handleAdd}>删除</button>
        </td>
      </div>
    );
  }


}

export default SingleOrder;