/**
 * Created by ppetrov on 10/14/2016.
 */

import 'whatwg-fetch';

export const ADD_TODO = 'BRKO@DEMO.ADD_TODO_ITEM';

export function addTodo(text) {
  return {
    type: ADD_TODO,
    text
  };
}

export function addTodoAsync(text) {
  return dispatch => {
    fetch('http://localhost:8080/demo')
      .then(response => {
        dispatch(addTodo(`${text} (with origin from): ${response}`));
      });
  };
}
