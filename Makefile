
.PHONY: all run pack test clear clean share model controler tests view cmdl
sources=src
models=$(sources)/model
shared=$(sources)/share
controler=$(sources)/controler
cmdl=$(sources)/cmdl

all: share model tests controler cmdl

share:
	cd $(sources)/share && \
	javac ICard.java ICardFactory.java \
	ISupportFill.java ISupportRepaint.java \
	ICardDeck.java ICardStack.java ICardHint.java ICardRepository.java

model: CardModel.class AbstractCardDeck.class CardDeckModel.class \
	CardStackModel.class CardPackModel.class \
	CardRepositoryModel.class CardModelFactory.class CardHintModel.class BoardModel.class

controler: ICommand.class CommandMove.class CommandMoveMulti.class \
	CommandNext.class CommandRenew.class ControlCommand.class CommandBuilder.class

cmdl: CommandLinePainter.class CommandLineKlondike.class CommandTranslator.class

tests:
	javac -cp "lib/junit-4.12.jar:." $(sources)/$@/KlondikeModelTester.java

CardModel.class: $(models)/CardModel.java
	javac $<
AbstractCardDeck.class: $(models)/AbstractCardDeck.java $(shared)/ICardDeck.java
	javac $<
CardDeckModel.class: $(models)/CardDeckModel.java $(models)/AbstractCardDeck.java
	javac $<
CardStackModel.class: $(models)/CardStackModel.java $(models)/AbstractCardDeck.java $(shared)/ICardStack.java
	javac $<
CardPackModel.class: $(models)/CardPackModel.java $(models)/AbstractCardDeck.java
	javac $<
CardRepositoryModel.class: $(models)/CardRepositoryModel.java $(models)/AbstractCardDeck.java $(shared)/ICardRepository.java
	javac $<	
CardModelFactory.class: $(models)/CardModelFactory.java $(shared)/ICardFactory.java
	javac $<
CardHintModel.class: $(models)/CardHintModel.java $(shared)/ICardStack.java
	javac $<
BoardModel.class: $(models)/BoardModel.java
	javac $<


ICommand.class: $(controler)/ICommand.java
	javac $<
CommandMove.class: $(controler)/CommandMove.java
	javac $<
CommandMoveMulti.class: $(controler)/CommandMoveMulti.java
	javac $<
CommandNext.class: $(controler)/CommandNext.java
	javac $<
CommandRenew.class: $(controler)/CommandRenew.java
	javac $<
ControlCommand.class: $(controler)/ControlCommand.java
	javac $<
CommandBuilder.class: $(controler)/CommandBuilder.java
	javac $<

CommandLinePainter.class: $(cmdl)/CommandLinePainter.java
	javac $<
CommandLineKlondike.class: $(cmdl)/CommandLineKlondike.java
	javac $<
CommandTranslator.class: $(cmdl)/CommandTranslator.java
	javac $<

run:
	java src.cmdl.CommandLineKlondike

test:
	java -cp "lib/junit-4.12.jar:.:lib/hamcrest-core-1.3.jar" \
		org.junit.runner.JUnitCore \
		src.tests.KlondikeModelTester

pack:
	zip -r xhrstk02.zip src src/* doc doc/* lib examples Makefile build.xml readme.txt -x lib/*

clear: clean
clean:
	cd $(sources)/model && rm -f *.class
	cd $(sources)/tests && rm -f *.class
	cd $(sources)/share && rm -f *.class
	cd $(sources)/view && rm -f *.class
	cd $(sources)/controler && rm -f *.class
	cd $(sources)/cmdl && rm -f *.class
