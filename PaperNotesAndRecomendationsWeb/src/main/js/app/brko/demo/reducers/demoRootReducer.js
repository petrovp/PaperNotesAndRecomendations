/**
 * Created by ppetrov on 10/14/2016.
 */

import {
  ADD_TODO,
} from '../actions/demoActions';

import initialDemoState from './initialDemoState';

import { combineReducers } from 'redux';
import { callReducerByMapping } from '../../redux-config/reducers';

export default function demoReducer() {
  return combineReducers({
    todos: callReducerByMapping({
      [ADD_TODO]: addTodo
    }, initialDemoState.todos),
  });
}

export function getTodos(state) {
  return state.demo.todos;
}

function addTodo(state, action) {
  const newTodo = {
    text: action.text
  };
  return [
    ...state,
    newTodo
  ];
}
