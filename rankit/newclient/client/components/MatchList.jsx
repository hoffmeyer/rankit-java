// @flow
import React from 'react'
import type { State, Match, List, Player } from '../types'

function playerIdToName( id: number, list:List) {
  const it = list.filter( (player) => player.id === id)
  if( it.length > 0 ) {
    return it[0].name;
  }
  return id;
}

function toListElement(match: Match, list: List)  {

  const playersToString = (players) => {
    return players.map((id) => playerIdToName( id, list)).join( ' & ');
  };

  const options = {
    year: "2-digit", month: "numeric",
    day: "numeric", hour: "2-digit", minute: "2-digit"
  };

  return  <tr>
            <td>
              {new Date(match.time).toLocaleDateString("da-DK", options)}
            </td>
            <td className="text-center">
              {playersToString(match.team1.players)}<br/><b>vs</b><br/>{playersToString(match.team2.players)}
            </td>
            <td className="text-center">
              {match.team1.score + ' - ' + match.team2.score}
            </td>
            <td className="text-right">
              {Math.abs(match.points) }
            </td>
          </tr>;
}

function MatchList(state: State)  {
  return  <div>
            <h1>Matches</h1>
            <table className="table table-striped table-hover">
              <thead>
                <tr>
                  <th>Time</th>
                  <th className="text-center">Players</th>
                  <th className="text-center">Score</th>
                  <th className="text-right">Points</th>
                </tr>
              </thead>
              <tbody>
                {state.matches.map( (match) => toListElement( match, state.list))}
              </tbody>
            </table>
          </div>;
}

export default MatchList;
