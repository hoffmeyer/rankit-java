// @flow
import {combineReducers} from 'redux';
import {
  INCREMENT,
  DECREMENT
} from '../actions'
import type {State} from '../types'

const count = (state = 0, action) => {
  switch (action.type){
    case INCREMENT:
      console.log( "count: " + state)
      return state + 1
    case DECREMENT:
      return state - 1
    default:
      return state
  }
}

const rootReducer = combineReducers({
  count
});

export default rootReducer;
