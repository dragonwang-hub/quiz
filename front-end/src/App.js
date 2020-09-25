import React from 'react';
import { Route, BrowserRouter, Switch, Link } from "react-router-dom";
import Home from "./Home";
import Order from "./Order";
import AddGoods from "./AddGoods";
import './App.css';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <nav className="navbar navbar-dark bg-primary bg-light">
          <Link to="/" className="nav-link" id="nav-first">
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
          <Route exact path="/order" component={Order} />
          <Route exact path="/addgoods" component={AddGoods} />
          <Route exact path="/" component={Home} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
