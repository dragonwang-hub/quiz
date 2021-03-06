import React, { Component } from 'react';
import { Route, BrowserRouter, Switch, Link } from "react-router-dom";
import Home from "./Home";
import Orders from "./Orders";
import AddGoods from "./AddGoods";
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
        <BrowserRouter>
          <nav className="navbar navbar-dark bg-primary bg-light">
            <Link to="/home" className="nav-link" id="nav-first">
              商城
            </Link>
            <Link to="/order" className="nav-link">
              订单
            </Link>
            <Link to="/addgoods" className="nav-link">
              添加商品
            </Link>
          </nav>
          <Switch>
            <Route exact path="/order" component={Orders} />
            <Route exact path="/addgoods" component={AddGoods} />
            <Route exact path="/Home" component={Home} />
            <Route exact path="/" component={Home} />
          </Switch>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
