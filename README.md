# deck-of-cards [![Build Status](https://travis-ci.org/Keratomi/deck-of-cards.svg?branch=master)](https://travis-ci.org/Keratomi/deck-of-cards) [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Keratomi_deck-of-cards&metric=alert_status)](https://sonarcloud.io/dashboard?id=Keratomi_deck-of-cards) [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Keratomi_deck-of-cards&metric=coverage)](https://sonarcloud.io/dashboard?id=Keratomi_deck-of-cards)
A Basic Deck of Cards Game

#### Usages:
* Main application URL: http://localhost:8080

##### Available URLs:
* /createGame - Create a new empty game
* /createDeck - Create a new, unshuffled deck with 52 cards
* /shuffleDeck?deckId={id} - Shuffle the given deck
* /addDeckToGame?gameId={id}&deckId={id} - Add given existing deck to given existing game
* /addPlayerToGame?gameId={id} - Create a new player and add to the given existing game
* /dealCardToAPlayer?playerId={id} - Deal cards to the given existing player
* /getAListOfCardsForPlayer?playerId={id} - Show the list of dealed cards for the given existing player 

##### Todo:
* Deletions
* Tests (unit and integration)
* Expected functionality (remove, card counts, etc.)

##### I would like to test Travis-CI
* add .travis.yml
