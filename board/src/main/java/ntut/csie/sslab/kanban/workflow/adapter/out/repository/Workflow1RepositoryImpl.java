package ntut.csie.sslab.kanban.workflow.adapter.out.repository;

import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;

import javax.ws.rs.NotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Workflow1RepositoryImpl implements Workflow1Repository {
    private final List<Workflow1> store = new ArrayList<>();

    public Workflow1RepositoryImpl(){
    }

    @Override
    public void deleteById(String s) {
        throw new NotSupportedException();
    }
    @Override
    public List<Workflow1> getWorkflowsByBoardId(String boardId) {
        throw new NotSupportedException();
    }

    public void save(Workflow1 workflow1){
        store.add(workflow1);
    }
    public Optional<Workflow1> findById(String Id){
        return store.stream().filter(workflow1 -> workflow1.getId().equals(Id)).findFirst();
    }

}
