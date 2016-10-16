/**
 * Created by ppetrov on 10/14/2016.
 */

import reduxThunkMiddleware from 'redux-thunk';
import { createStore, compose, applyMiddleware } from 'redux';
import { routerMiddleware } from 'react-router-redux';

import { rootReducer } from './reducers';

export default function configureStore(historyToUse) {
  const defaultMiddlewares = getDefaultMiddlewares(historyToUse);

  const middlewares = [...defaultMiddlewares];

  const storeEnhancers = [
    applyMiddleware(...middlewares),
    reduxDevToolsHookEnhancer() // for development only
  ];

  return createStore(rootReducer, compose(...storeEnhancers));
}

function getDefaultMiddlewares(historyToUse) {
  return [
    routerMiddleware(historyToUse),
    reduxThunkMiddleware
  ];
}

function reduxDevToolsHookEnhancer() {
  if (typeof window.devToolsExtension === 'function') {
    return window.devToolsExtension();
  }
  return identity;
}

function identity(args) {
  return args;
}
