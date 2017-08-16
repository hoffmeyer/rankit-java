// @flow
import {combineReducers} from 'redux';
import {
  REQUEST_PLAYER,
  RECEIVE_PLAYER,
  REQUEST_LIST,
  RECEIVE_LIST,
  REQUEST_MATCHES,
  RECEIVE_MATCHES
} from '../actions'
import type {
  State,
  Player,
  Action,
  List,
  Match
} from '../types';

function player(state: ?Player = null, action: Action): ?Player {
  switch(action.type){
    case RECEIVE_PLAYER:
      return action.player
    default:
      return state;
  }
}

function matches(state: Array<Match> = [], action: Action): Array<Match> {
  switch (action.type){
    case RECEIVE_MATCHES:
      return action.matches
    default:
      return state
  }
}

function list(state: List = [] , action: Action): List  {
  switch (action.type){
    case RECEIVE_LIST:
      return action.list
    default:
      return state
  }
}

function isPlayerLoading (state = false, action: Action): boolean {
  switch (action.type){
    case REQUEST_PLAYER:
      return true;
    case RECEIVE_PLAYER:
      return false;
    default:
      return state;
  }
}

function isListLoading (state = false, action: Action): boolean {
  switch (action.type){
    case REQUEST_LIST:
      return true
    case RECEIVE_LIST:
      return false
    default:
      return state;
  }
}

function isMatchesLoading (state = false, action: Action): boolean {
  switch (action.type){
    case REQUEST_MATCHES:
      return true;
    case RECEIVE_MATCHES:
      return false;
    default:
      return state;
  }
}

const rootReducer = combineReducers({
  player,
  list,
  matches,
  isListLoading,
  isMatchesLoading
});

export default rootReducer;
