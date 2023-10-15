package gestorAplicacion.entidad.producto;

public enum Categoria {
    // Enum de categorias MP
    ELECTRONICA(false),
    ROPA(false),
    HOGAR(false),
    ALIMENTOS(true),
    JUGUETES(false),
    COSMETICOS(false),
    OTROS(false);

    private boolean perecedero;

    Categoria(boolean perecedero) {
        this.perecedero = perecedero;
    }

    public boolean isPerecedero() {
        return perecedero;
    }
}
