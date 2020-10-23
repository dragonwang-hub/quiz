import React, { Component } from 'react';
import Good from "./Good";
import axios from 'axios';

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
    good_info: "",
  }
  componentWillMount = async () => {
    // const result = await fetch('http://localhost:3000/goods');
    const response = await axios.get('http://localhost:3000/goods')
    const data = await response.json();
    this.setState({
      goods: data
    })
  }

  render() {
    return (
      <div className="home page card">
        <p>This is a beautiful Home Page.</p>
        <p>And the url is '/'.</p>
        <div className="goods">
          {this.state.goods.map((good, index) => (
            <div key={index}>
              <Good className="good"
                eachGood={good}
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