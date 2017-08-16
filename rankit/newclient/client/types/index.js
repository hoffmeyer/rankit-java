// @flow
import type {Store as ReduxStore, Dispatch as ReduxDispatch} from 'redux'

export type Id = number

export type Player = {
  id: Id,
  name: string,
  points: number,
  gamesPlayed: number,
  lostGames: number,
  wonGames: number,
  mostWinsInRow: number,
  mostLossesInRow: number,
  currentWinsInRow: number,
  currentLossesInRow: number
}

export type Team = {
  score: number,
  players: Array<Id>
}

export type Match = {
  id: Id,
  time: Date,
  team1: Team,
  team2: Team,
  points: number
}

export type List = Array<Player>

export type State = {
  player: ?Player,
  list: List,
  matches: Array<Match>,
  isListLoading: boolean,
  isMatchesLoading: boolean
};

export type RequestPlayerAction = { type: 'REQUEST_PLAYER'};
export type ReceivePlayerAction = { type: 'RECEIVE_PLAYER', player: Player };
export type RequestListAction = { type: 'REQUEST_LIST' };
export type ReceiveListAction = { type: 'RECEIVE_LIST', list: List };
export type RequestMatchesAction = { type: 'REQUEST_MATCHES' };
export type ReceiveMatchesAction = { type: 'RECEIVE_MATCHES', matches: Array<Match> };

export type Action =
  | RequestPlayerAction
  | ReceivePlayerAction
  | RequestListAction
  | ReceiveListAction
  | RequestMatchesAction
  | ReceiveMatchesAction
  ;

export type Store = ReduxStore<State, Action>;

export type Dispatch = ReduxDispatch<Action>;
