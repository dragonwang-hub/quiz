import React, { Component } from 'react';
import Good from "./Good";

class Home extends Component {
  state = {
    goods: [
      {
        "name": "雪碧",
        "price": 3,
        "unit": "瓶",
        "imgUrl": "./"
      },
      {
        "name": "可乐",
        "price": 4,
        "unit": "瓶",
        "imgUrl": "./"
      },
    ],
  }
  async componentWillMount() {
    const result = await fetch('http://localhost:3000/goods');
    const data = await result.json();
    this.setState({
      goods: data
    })
  }
  handleAddOrder = () => {
    // 将数据post订单列表
  }


  render() {
    return (
      <div className="home page card">
        <p>This is a beautiful Home Page.</p>
        <p>And the url is '/'.</p>
        <div className="goods">
          {this.state.goods.map((good, index) => (
            <div key={index}>
              <Good className="phone"
                eachGood={good}
                handleAdd={this.props.handleAddOrder}
              >
              </Good>
            </div>
          ))
          }
        </div>
      </div>

    );
  }
}

export default Home;