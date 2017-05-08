// @flow
import {combineReducers} from 'redux';
import {
  REQUEST_LIST,
  RECEIVE_LIST
} from '../actions'
import type {State} from '../types'

const list = (state = [], action) => {
  switch (action.type){
    case RECEIVE_LIST:
      return action.list
    default:
      return state
  }
}

const isListLoading = (state = false, action) => {
  switch (action.type){
    case REQUEST_LIST:
      console.log('is now loading')
      return true
    case RECEIVE_LIST:
      console.log('finished loading')
      return false
    default:
      return state
  }
}

const rootReducer = combineReducers({
  list,
  isListLoading
});

export default rootReducer;
