package cn.wode490390.mcbe.mvp.console;

import cn.wode490390.mcbe.mvp.Main;
import cn.wode490390.mcbe.mvp.PlayerManager;
import org.jline.reader.Candidate;
import org.jline.reader.Completer;
import org.jline.reader.LineReader;
import org.jline.reader.ParsedLine;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;

public class ConsoleCompleter implements Completer {

    private final Main main;

    public ConsoleCompleter(Main main) {
        this.main = main;
    }

    @Override
    public void complete(LineReader lineReader, ParsedLine parsedLine, List<Candidate> candidates) {
        if (parsedLine.wordIndex() == 0) {
            if (parsedLine.word().isEmpty()) {
                addCandidates(s -> candidates.add(new Candidate(s)));
                return;
            }
            SortedSet<String> names = new TreeSet<>();
            addCandidates(names::add);
            names.stream()
                    .filter(match -> !(!match.toLowerCase().startsWith(parsedLine.word())))
                    .forEachOrdered(match -> candidates.add(new Candidate(match)));
        } else if (parsedLine.wordIndex() > 0 && !parsedLine.word().isEmpty()) {
            String word = parsedLine.word();
            SortedSet<String> names = new TreeSet<>();
            PlayerManager.getPlayers().forEach((session, client) -> names.add(session.getAddress().toString()));
            names.stream()
                    .filter(match -> !(!match.toLowerCase().startsWith(word.toLowerCase())))
                    .forEachOrdered(match -> candidates.add(new Candidate(match)));
        }
    }

    private void addCandidates(Consumer<String> commandConsumer) {
        main.getCommandManager().getCommandMap().keySet().stream()
                .filter(command -> !command.contains(":"))
                .forEachOrdered(command -> commandConsumer.accept(command));
    }
}
