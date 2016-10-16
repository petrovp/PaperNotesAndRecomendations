/**
 * Created by ppetrov on 10/14/2016.
 */

import React from 'react';
import { bindActionCreators } from 'redux';
import { connect } from 'react-redux';
import { Button } from 'react-bootstrap';

import * as DemoActions from '../actions/demoActions'
import {
  getTodos,
} from '../reducers/demoRootReducer';

import '../styles/Demo.scss';

class Demo extends React.Component {

  render() {
    const {
      todos,
      addTodo,
      addTodoAsync,
    } = this.props;

      const items = todos.map((todo, index) => <li key={index}>{todo.text}</li>);

    return (
      <div className="demo-module">
        <ul>
          {items}
        </ul>
        <hr />
        <div>
          <input
            type="text"
            ref={(input) => { this.input = input; }}
          />
          <Button onClick={() => addTodo(this.input.value)}>
            Add new
          </Button>
          <Button onClick={() => addTodoAsync(this.input.value)}>
            Add new (async)
          </Button>
        </div>
      </div>
    );
  }
}

Demo.propTypes = {
  todos: React.PropTypes.array,
  addTodo: React.PropTypes.func,
  addTodoAsync: React.PropTypes.func,
};

function mapStateToProps(state) {
  return {
    todos: getTodos(state)
  };
}

function mapDispatchToProps(dispatch) {
  const allActionCreators = Object.assign({}, DemoActions);
  return bindActionCreators(allActionCreators, dispatch);
}

export default connect(
    mapStateToProps,
    mapDispatchToProps
) (Demo);
