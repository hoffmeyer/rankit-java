// @flow
import React from 'react'
import type { State, Match } from '../types'

function toListElement(match: Match) {
    var playersToString = function(players){
      return players.join( ' & ');
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
  console.log('matchlist here')
  return  <table className="table table-striped table-hover">
            <thead>
              <tr>
                <th>Time</th>
                <th className="text-center">Players</th>
                <th className="text-center">Match score</th>
                <th className="text-right">Points</th>
              </tr>
            </thead>
            <tbody>
              {state.matches.map(toListElement)}
            </tbody>
          </table>;
}

export default MatchList;
