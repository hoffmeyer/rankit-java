// @flow
import type {
  Player,
  List,
  Action,
  Match,
  Dispatch,
  RequestPlayerAction,
  ReceivePlayerAction,
  RequestListAction,
  ReceiveListAction,
  RequestMatchesAction,
  ReceiveMatchesAction
} from '../types'

export const REQUEST_PLAYER  = 'REQUEST_PLAYER';
export const RECEIVE_PLAYER  = 'RECEIVE_PLAYER';
export const REQUEST_LIST    = 'REQUEST_LIST';
export const RECEIVE_LIST    = 'RECEIVE_LIST';
export const REQUEST_MATCHES = 'REQUEST_MATCHES';
export const RECEIVE_MATCHES = 'RECEIVE_MATCHES';

export const requestPlayer = (): RequestPlayerAction => ({
  type: REQUEST_PLAYER
});

export const receivePlayer = (player: Player): ReceivePlayerAction => ({
  type: RECEIVE_PLAYER,
  player: player
});

export const requestList = (): RequestListAction => ({
  type: REQUEST_LIST
});

export const receiveList = (list: List): ReceiveListAction => ({
  type: RECEIVE_LIST,
  list: list
});

export const requestMatches = (): RequestMatchesAction => ({
  type: REQUEST_MATCHES
});

export const receiveMatches = (matches: Array<Match>): ReceiveMatchesAction => ({
  type: RECEIVE_MATCHES,
  matches: matches
});

export const fetchPlayer = (dispatch: Dispatch) => {
  dispatch(requestPlayer);
  fetch('http://localhost:8338/api/player/dummyId')
  .then( response => response.json())
  .then( json => dispatch( receivePlayer(json)));
}

export const fetchList = (dispatch: Dispatch) => {
  dispatch(requestList);
  fetch('http://localhost:8338/api/list')
  .then( response => response.json())
  .then( json => dispatch( receiveList(json)));
}

export const fetchMatches = (dispatch: Dispatch) => {
  dispatch(requestMatches);
  fetch('http://localhost:8338/api/match/100')
  .then( response => response.json())
  .then( json => dispatch( receiveMatches(json)));
}
