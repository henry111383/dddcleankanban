package ntut.csie.sslab.kanban.board.adapter.out.repository;

import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBoardRepository implements BoardRepository {
    private List<Board> store = new ArrayList<>();
    @Override
    public Optional<Board> findById(String Id) {
        return store.stream().filter(Board -> Board.getId().equals(Id)).findFirst();
    }

    @Override
    public void save(Board data) {
        store.add(data);
    }

    @Override
    public void deleteById(String s) {

    }
}
