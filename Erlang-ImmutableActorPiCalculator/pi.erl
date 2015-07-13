-module(pi).
-export([loop/0]).
-export([launch/1]).

% actor-loop
loop() ->
		receive
		{From, ChunkNr} when erlang:is_integer(ChunkNr) ->
			Chunk = 4.0 * (1.0 - mod(ChunkNr,2) * 2.0) / (2.0 * ChunkNr + 1.0),
			From ! {From, Chunk},
			loop();
		{'EXIT'} ->
			exit({pi,die,at,erlang:time()})
end.

mod(X,Y)->(X rem Y + Y) rem Y.

% launch actors
launch(NrOfChunks) -> launch(NrOfChunks,NrOfChunks).
launch(-1,NrOfChunks) -> collect(NrOfChunks);
launch(I,NrOfChunks) -> 
	PiD = spawn(fun pi:loop/0),
	PiD ! {self(), I},
launch(I-1,NrOfChunks).

% collect result
collect(NrOfChunks) -> collect(NrOfChunks,0.0).
collect(-1,Pi) -> 
	io:format("~.18g~n",[Pi]), 
	Pi;
collect(I,Pi) ->
	receive
		{From, Chunk} -> 
			From ! {'EXIT'},
			collect(I-1, Pi + Chunk)
	end.
