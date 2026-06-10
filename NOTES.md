# Notes
- Aco manages the Anion side of the mod
- Chem manages the Electrical side of the mod

- Aco codes for whichever she feels like
- Chem codes for both since Aco does assets
:3

- Both sides of the mod must be able to work together, and not clash in concepts or designs.
- All blocks and items fork from Charged Iron or other Charged materials.

# Concepts
## Charging other materials
- Gold is naturally conductive, and could be used for certain electrical bits.
- Copper is also conductive.
- Diamonds are thermally conductive.
- No idea for emeralds other than they symbolize good health

## Anion whatnots
- Something related to DNA (I don't have the brain capacity to read this in full rn)
- `https://pubs.rsc.org/en/content/articlelanding/2009/cp/b910690a`

# Batteries (or my vision of them)
- Batteries start by storing zero shockies (0S)
- Upon use on a charged Volt-eater, the Volt-eater's charges will transfer to the Battery, or combine.
- Batteries then are to be used on other electrical blocks to charge them.

# Shockies
An electricity measurement for large amounts of power in a small number.

`1 shockie = 3x10^8 volts`

Scientific Unit: S

Shockies are used for calculating the electrical energy in lightning as a smaller number.

# Electrical Blocks
## Volt-eaters
Volt-eaters will, upon being struck by lightning, store the lightning as a Shockie. This power can then be transmitted to Batteries or to other electrical blocks that are touching the Volt-eater.

Power is distributed among the touching machines as so:

```
(int) round(total power / amount of machines)
```

mockup ^

## Attractors
(Chargeable)

Requires a Hopper to be placed below the Attractor.

When charged and a Hopper is detected, it will pull all nearby dropped items into the Hopper. Depletes one charge randomly if a condition is met on random ticks.

## Conductors (concept)
(Chargeable)

When powered, will damage nearby enemies using arcing electricity. This electricity will spread to nearby enemies, dealing more damage the more entities nearby.