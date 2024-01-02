package Pages;

import BackEnd.Cinema;
import BackEnd.Movie;
import BackEnd.User;
import CustomComponent.Text.MyText;
import CustomComponent.Text.MyTextField;
import CustomComponent.CustomPanel.PanelRound;
import CustomComponent.ScrollPane.ScrollPaneWin11;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import static CustomComponent.StaticClass.Properties.*;

public class Comment extends PanelRound {
    private ArrayList<CommentInfo> commentInfos = new ArrayList<>();

    MyText userInput;
    MyText userName;
    private JPanel commentsPanel;
    MyTextField userCommentInput;
    Movie movie;

    Comment(Map<User, ArrayList<String>> comments, Movie movie) {
        this.movie = movie;
        if (comments != null) {
            for (Map.Entry<User, ArrayList<String>> entry : comments.entrySet()) {
                String name = entry.getKey().getName();
                for (String c : entry.getValue()) {
                    String comment = c;
                    CommentInfo commentInfo = new CommentInfo(name, comment);
                    commentInfos.add(commentInfo);
                }
            }
        }
        this.setRoundBottomLeft(40);
        this.setRoundBottomRight(40);
        this.setRoundTopLeft(40);
        this.setRoundTopRight(40);
        this.setBackground(dark_Gray);
        this.setLayout(new MigLayout("debug,insets 3% 3% 3% 3%", "[]3%[]5%[]", "[]5%[]"));

        MyText comment = new MyText("Comments", 40, Color.white, 1);
        comment.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#373737")));
        this.add(comment, "pushx,growx,wrap,left");

        userCommentInput = new MyTextField(Color.WHITE);
        userCommentInput.setForeground(white_);
        userCommentInput.setHint("Add a comment");
        userCommentInput.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#373737")));
        this.add(userCommentInput, "w 30% ,pushx,growx,wrap");

        MigLayout layout = new MigLayout("wrap, gapx 2%,ax center", "push[center]push");
        commentsPanel = new JPanel(layout);
        commentsPanel.setBackground(dark_Gray);
        refreshLabelPanel();
        if (isPanelEmpty(commentsPanel)) {
            MyText noComment = new MyText("No comments yet", 18, white_, 0);
            MyText massage = new MyText("say something to start the conversation", 15, Color.decode("#373737"), 0);

            commentsPanel.add(noComment, "wrap");
            commentsPanel.add(massage, "wrap");
        } else {
            layout.setLayoutConstraints("wrap, gapx 2% ");
            layout.setColumnConstraints("");
        }

        userCommentInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComment();
                layout.setLayoutConstraints("wrap, gapx 2% ");
                layout.setColumnConstraints("");
                userCommentInput.setText("");

            }
        });
        JScrollPane scrollPane = new ScrollPaneWin11(commentsPanel);
        scrollPane.setBackground(dark_Gray);
        scrollPane.setBorder(BorderFactory.createLineBorder(dark_Gray, 2));
        this.add(scrollPane, "w 100%");
    }

    private void addComment() {
        String comment = userCommentInput.getText();
        if (comment != null && !comment.isEmpty()) {
            CommentInfo commentInfo = new CommentInfo(user.getName(), comment);
            commentInfos.add(commentInfo);
            Cinema cinema = new Cinema();
            cinema.leaveComment(user, movie, comment);
        }
        refreshLabelPanel();
    }

    private void refreshLabelPanel() {
        commentsPanel.removeAll();

        for (CommentInfo commentInfo : commentInfos) {
            JPanel panel = new JPanel(new MigLayout("wrap"));
            MyText name = new MyText(commentInfo.getName(), 10, Color.decode("#8d8e92"), 0);
            MyText comment = new MyText(commentInfo.getComment(), 13, white_, 1);
            panel.setBackground(dark_Gray);
            panel.add(name);
            panel.add(comment);
            commentsPanel.add(panel);
        }

        commentsPanel.revalidate();
        commentsPanel.repaint();
    }

    private static class CommentInfo {
        private String name;
        private String comment;

        public CommentInfo(String name, String comment) {
            this.name = name;
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public String getComment() {
            return comment;
        }
    }

    private static boolean isPanelEmpty(JPanel panel) {
        Component[] components = panel.getComponents();
        return components.length == 0;
    }
}
