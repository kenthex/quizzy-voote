const Sequelize = require('sequelize');
const db = require('../config/database');
const Token = require('../models/Token');
const Vote = require('../models/Vote');
const Question = require('../models/Question');

const User = db.define('users', {
  id: {
    type: Sequelize.INTEGER(),
    primaryKey: true,
    autoIncrement: true,
    unique: true,
    allowNull: false
  },

  login: {
    type: Sequelize.STRING(),
    unique: true,
    allowNull: false,
    validate: {
      notNull: false,
      len: [4, 12],
      is: /^[a-z0-9_-]+$/i
    }
  },

  email: {
    type: Sequelize.STRING(),
    unique: true,
    allowNull: false,
    validate: {
      isEmail: true,
      notNull: false,
      len: [10, 25],
      is: /^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z\.])+$/i
    }
  },

  password: {
    type: Sequelize.STRING(),
    allowNull: false,
    validate: {
      len: [8, 24]
    }
  }

});

// Relation to tokens (1-M)
User.hasMany(Token, { foreignKey: 'user_id' });
Token.belongsTo(User, { foreignKey: 'user_id' });
// Relation to votes (1-1)
User.hasOne(Vote, { foreignKey: 'user_id' });
Vote.belongsTo(User, { foreignKey: 'user_id' });
// Relation to questions (1-M)
User.hasMany(Question, { as: 'question', foreignKey: 'creator' });
Question.belongsTo(User, { as: 'user_data', foreignKey: 'creator' });

module.exports = User;
