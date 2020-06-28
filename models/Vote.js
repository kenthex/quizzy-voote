const Sequelize = require('sequelize');
const db = require('../config/database');
const Answer = require('../models/Answer');

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
    //unique: true,
    allowNull: false
  },

  question_id: {
    type: Sequelize.INTEGER(),
    //unique: true,
    allowNull: false
  }
});

Vote.belongsTo(Answer, { as: 'answer_data', foreignKey: 'answer_id' });

module.exports = Vote;
