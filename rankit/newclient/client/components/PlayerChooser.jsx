import React from 'react'
import {State, Player} from '../types'

const toPlayerElement = (player: Player, index: number) => (
  <option value={player.id}>{player.name}</option>
)

export const PlayerChooser = ({list}: State) => (
  <select>
    <option>Choose wisely...</option>
    {list.map(toPlayerElement)}
  </select>
)
