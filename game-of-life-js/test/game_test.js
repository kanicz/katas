/*global describe,it, beforeEach*/
'use strict';
var assert = require('assert'),
    game = require('../lib/game.js').game;

describe('Game of life', function () {

    beforeEach(function () {
        game.setWorldSize(4, 4);
    });

    it('should have desired world size', function () {
        assert.equal(game.worldWidth, 4);
        assert.equal(game.worldHeight, 4);
    });

    it('should kill alone cell', function () {
        game.setCellAlive(1, 2);
        game.tick();
        assert.equal(game.isCellAlive(1, 2), false);
    });

    it('should bring to life cell with three neighbours', function () {
        game.setCellAlive(1, 1);
        game.setCellAlive(2, 2);
        game.setCellAlive(1, 3);
        game.tick();
        assert.equal(game.isCellAlive(1, 2), true);
    });

    it('should not bring to life cell with four neighbours', function () {
        game.setCellAlive(1, 1);
        game.setCellAlive(2, 2);
        game.setCellAlive(0, 2);
        game.setCellAlive(1, 3);
        game.tick();
        assert.equal(game.isCellAlive(1, 2), false);
    });

    it('should keep alive cell with two neighbours', function () {
        game.setCellAlive(1, 1);
        game.setCellAlive(1, 2);
        game.setCellAlive(1, 3);
        game.tick();
        assert.equal(game.isCellAlive(1, 2), true);
    });

    it('should keep alive cell with three neighbours', function () {
        game.setCellAlive(1, 1);
        game.setCellAlive(1, 2);
        game.setCellAlive(1, 3);
        game.setCellAlive(2, 2);
        game.tick();
        assert.equal(game.isCellAlive(1, 2), true);
    });

    it('should kill cell with one neighbour', function () {
        game.setCellAlive(1, 1);
        game.setCellAlive(1, 2);
        game.tick();
        assert.equal(game.isCellAlive(1, 2), false);
    });
});
