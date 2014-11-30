'use strict';

exports.game = {

    neighboursCoordinates: [[-1, -1], [-1, 0], [-1, 1], [1, -1], [1, 0], [1, 1], [0, -1], [0, 1]],
    DEAD_CELL: 0,
    ALIVE_CELL: 1,

    score: function (x, y) {
        var score = 0;
        var that = this;

        function isInsideWorld() {
            return x > 0 && x < that.worldWidth - 1 && y > 0 && y < that.worldHeight - 1;
        }

        this.neighboursCoordinates.forEach(function (coordinate) {
            if (isInsideWorld()) {
                score += that.world[x + coordinate[0]][y + coordinate[1]];
            }
        }, this.world);
        return score;
    },

    tick: function () {
        var newWorld = this.createWorld();

        function cellShouldLive(cellIsAlive, score) {
            if (cellIsAlive) {
                return score === 2 || score === 3 ? 1 : 0;
            } else {
                return score === 3 ? 1 : 0;
            }
        }

        for (var i = 0; i < this.worldWidth; i++) {
            for (var j = 0; j < this.worldHeight; j++) {
                var score = this.score(i, j);
                var cellIsAlive = (this.world[i][j] !== this.DEAD_CELL);
                newWorld[i][j] = cellShouldLive(cellIsAlive, score);
            }
        }
        this.world = newWorld;
    },

    setWorldSize: function (worldWidth, worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.world = this.createWorld(worldWidth, worldHeight);
    },

    createWorld: function () {
        var newWorld = new Array(this.worldHeight);
        for (var i = 0; i < this.worldHeight; i++) {
            newWorld[i] = [];
            for (var j = 0; j < this.worldHeight; j++) {
                newWorld[i].push(this.DEAD_CELL);
            }
        }
        return newWorld;
    },

    setCellAlive: function (x, y) {
        this.world[x][y] = this.ALIVE_CELL;
    },

    isCellAlive: function (x, y) {
        return this.world[x][y] === this.ALIVE_CELL;
    }
};


