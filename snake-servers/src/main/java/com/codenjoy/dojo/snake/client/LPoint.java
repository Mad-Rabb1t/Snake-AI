package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 - 2020 Codenjoy
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

public class LPoint {
  public final int x;
  public final int y;

  public LPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static LPoint of(int x, int y) {
    return new LPoint(x, y);
  }

  public LPoint move(LPoint delta) {
    return LPoint.of(
        x + delta.x,
        y + delta.y
    );
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    LPoint lPoint = (LPoint) o;

    if (x != lPoint.x) return false;
    return y == lPoint.y;
  }

  @Override
  public int hashCode() { // int size: 0..2^32
    return x << 16 + y;   // 0..2^16
  }

  @Override
  public String toString() {
    return String.format("[%2d:%2d]", x, y);
  }

  public static void main(String[] args) {
    LPoint p1 = new LPoint(1, 2);
    LPoint p2 = LPoint.of(1, 2);
  }

}
