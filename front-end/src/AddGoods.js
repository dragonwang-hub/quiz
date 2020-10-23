import React, { Component } from 'react';
import axios from 'axios';

class AddGoods extends Component {
  state = {
    name: "",
    price: "",
    unit: "",
    imgUrl: "",
  };

  handleFieldChange = (field, event) => {
    this.setState({
      [field]: event.target.value,
    });
  };
  handleFormSubmit = async (e) => {
    e.preventDefault();
    alert(JSON.stringify(this.state));
    const good = [{
      "name" : this.state.name,
      "price" : this.state.price,
      "unit" : this.state.unit,
      "imgUrl" : this.state.imgUrl,
    }]
    await axios.post('http://localhost:8080/addgood', good)
  };



  render() {
    return (
      <div className="container">
        <h1>添加商品</h1>
        <form className="addGoods" onSubmit={this.handleFormSubmit}>
          <div className="form-group">
            <label htmlFor="name">商品</label>
            <input
              type="text"
              value={this.state.name}
              onChange={(e) => this.handleFieldChange("name", e)}
              className="form-control"
              id="name"
            />
          </div>
          <div className="form-group">
            <label htmlFor="name">价格</label>
            <input
              type="number"
              value={this.state.price}
              onChange={(e) => this.handleFieldChange("price", e)}
              className="form-control"
              id="price"
            />
          </div>
          <div className="form-group">
            <label htmlFor="unit">单位</label>
            <input
              type="text"
              value={this.state.unit}
              onChange={(e) => this.handleFieldChange("unit", e)}
              className="form-control"
              id="unit"
            />
          </div>
          <div className="form-group">
            <label htmlFor="imgUrl">图片</label>
            <input
              type="text"
              value={this.state.imgUrl}
              onChange={(e) => this.handleFieldChange("imgUrl", e)}
              className="form-control"
              id="unit"
            />
          </div>
          <div className="form-group">
            <input
              disabled={!this.state.name || !this.state.price || !this.state.unit || !this.state.imgUrl}
              type="submit"
              value="Submit"
              className="btn btn-primary"
            />
          </div>
        </form>
      </div>
    );
  }
}

export default AddGoods;