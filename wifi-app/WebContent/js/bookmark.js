function deleteBookMark(dataId) {
    if (confirm("삭제하시겠습니까?")) {
        window.location.href = "delete-bookmark.jsp?bookmarkId=" + dataId;
    }
}