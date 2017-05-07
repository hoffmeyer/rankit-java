// @flow

import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App.jsx';
import {Provider} from 'react-redux';
import reducer from './reducers';
import {createStore, applyMiddleware} from 'redux'
import styles from './styles.css';
import type {Store} from './types';
import thunk from 'redux-thunk';

const middleware = [thunk];

const store: Store = createStore(
  reducer,
  applyMiddleware(...middleware)
);

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root'));
