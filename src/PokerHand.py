import sys
from filecmp import cmp

sys.path.append(r'/Users/lichuwen/BootCamp/TDD-poker-hands')
from src.PokerHandType import PokerHandType
from collections import Counter, OrderedDict
from functools import cmp_to_key

BLACK = "black"
WHITE = "white"
TIE = "tie"

pokerHand = PokerHandType()


def compare(x, y):
    if x[1] < y[1]:
        return 1
    elif x[1] == y[1]:
        if x[0] < y[0]:
            return 1
        elif x[0] == y[0]:
            return 0
        else:
            return -1
    else:
        return -1


class PokerHand:

    def __init__(self):
        pass

    def playCard(self, whiteCards, blackCards):
        whiteCounter = Counter(pokerHand.getNumber(whiteCards))
        blackCounter = Counter(pokerHand.getNumber(blackCards))

        whiteCardSort = sorted(whiteCounter.items(), key=cmp_to_key(compare))
        blackCardSort = sorted(blackCounter.items(), key=cmp_to_key(compare))

        whiteCardByKey = [k for k, v in whiteCardSort]
        blackCardByKey = [k for k, v in blackCardSort]

        if whiteCardByKey < blackCardByKey:
            return BLACK
        elif whiteCardByKey > blackCardByKey:
            return WHITE
        else:
            return TIE
