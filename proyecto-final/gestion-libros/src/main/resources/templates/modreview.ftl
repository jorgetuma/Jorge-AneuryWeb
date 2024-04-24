<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar reseña</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="modreview" id="reviewModal" tabindex="-1" aria-labelledby="reviewModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="reviewModalLabel">Modificar Reseña</h5>
            </div>
            <div class="modal-body">
                <form id="reviewForm" method="post" action="/review/edit/${review.idReview}&${idlibro}">
                    <div class="mb-3">
                        <label for="review" class="form-label">Reseña:</label>
                        <textarea name="review" class="form-control" id="review" rows="3">${review.review}</textarea>
                    </div>
                    <div class="mb-3">
                        <label for="calificacion" class="form-label">Calificación (0-10):</label>
                        <input name="calificacion" type="number" class="form-control" id="calificacion" value="${review.calificacion}" min="0" max="10">
                    </div>

                    <div class="modal-footer">
                        <a href="/review/ver/${idlibro}"><button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button></a>
                        <button type="submit" class="btn btn-primary">modificar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>