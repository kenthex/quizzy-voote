const Sequelize = require('sequelize');
const db = require('../config/database');

const Vote = db.define('votes', {
  id: {
    type: Sequelize.INTEGER(),
    primaryKey: true,
    autoIncrement: true,
    unique: true,
    allowNull: false
  },

  answer_id: {
    type: Sequelize.INTEGER(),
    allowNull: false
  },

  user_id: {
    type: Sequelize.INTEGER(),
    unique: true,
    allowNull: false
  },

  question_id: {
    type: Sequelize.INTEGER(),
    unique: true,
    allowNull: false
  }

});

module.exports = Vote;
