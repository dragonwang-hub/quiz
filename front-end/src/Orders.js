import React, { Component } from 'react';
import SingleOrder from './Order';

class Orders extends Component {
  state = {
    orders: [
      {
        "name": "雪碧",
        "price": 3,
        "unit": "瓶",
        "count": 1
      },
      {
        "name": "可乐",
        "price": 4,
        "unit": "瓶",
        "count": 4
      },
    ],
  }
  async componentWillMount() {
    const result = await fetch('http://localhost:3000/orders');
    const data = await result.json();
    this.setState({
      orders: data
    });
  };

  render() {
    return (
      <div className="orders page card">
        <p>This is a beautiful Order Page.</p>
        <p>And the url is '/orders'.</p>
        <table className="tableLabel">
          <thead>
            <th scope="row">名字</th>
            <th scope="row">单价</th>
            <th scope="row">数量</th>
            <th scope="row">单位</th>
            <th scope="row">操作</th>
          </thead>
          <div className="orders">
            {this.state.orders.map((order, index) => (
              <tr key={index}>
                <SingleOrder className="order"
                  eachOrder={order}
                >
                </SingleOrder>
              </tr>
            ))
            }
          </div>
        </table>
      </div>
    );
  }
}

export default Orders;