## Balloon
### A turing complete visual programming language
#### Control
` ` A space. This character is passed through.
`/` Does nothing. Can be used for visually dividing sections
#### Moving
`v` / `↓` Moves electricity down.
`^` / `↑` Moves electricity up.
`<` / `←` Moves electricity left.
`>` / `→` Moves electricity right.
`+`       Splits electricity. When powered, it powers all other cells in it's Von Neumann neighborhood, excluding itself. This means that it could power the thing that powered it; be careful.
#### Gates
`!` / `n` Not gate. Powers the cell below it if the one above it isn't powered. If the cell above it is powered, it does nothing.
`o` / `O` Or gate. Powers the cell below it if the left or right cells are powered.
`x` / `X` Xor gate. Powers the cell below it if exactly one of the left or right cells are powered.
`a` / `A` And gate. Powers the cell below it if both the left and right cells are powered.
`@`       Nand gate. Powers the cell below if 1 or fewer of the left and right cells are powered.
#### Skips
`D` / `▼` Skip down 2 cells. Can be used for crossing paths.
`U` / `▲` Skip up 2 cells. Can be used for crossing paths
`L` / `◄` Skip left 2 cells. Can be used for crossing paths.
`R` / `►` Skip right 2 cells. Can be used for crossing paths.
#### Power sources
`e` / `♦` Emitter. Constantly emits power.
`p` / `♣` Pulse. Outputs power for the first tick and then turns into an empty cell.
#### Outputs
`0`       Success. Exits program with code 0.
`1`       Fail. Exits program with code 1.
