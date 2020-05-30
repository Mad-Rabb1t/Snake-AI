package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());

        Lee lee = new Lee(15,15);
        Optional<List<LPoint>> path = Optional.empty();
        if(board.getHead() != null) {
            LPoint start = LPoint.of(board.getHead().getX(), board.getHead().getY());

            LPoint target = LPoint.of(board.getApples().get(0).getX(), board.getApples().get(0).getY());
            List<LPoint> obstacles = board.getBarriers().stream().map(p -> LPoint.of(p.getX(), p.getY()))
                    .collect(Collectors.toList());
            path = lee.trace(start, target, obstacles);
        }


        if(path.isPresent()){
            System.out.println(path);
            LPoint next_step = path.get().get(1);
            if      (next_step.x < board.getHead().getX()) return "LEFT";
            else if (next_step.x > board.getHead().getX()) return "RIGHT";
            else if (next_step.y > board.getHead().getY()) return "UP";
            else if (next_step.y < board.getHead().getY()) return "DOWN";

        }
        return "";
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://167.71.55.144/codenjoy-contest/board/player/v5al9gqy92j742q3klpu?code=4157785706490913199",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
